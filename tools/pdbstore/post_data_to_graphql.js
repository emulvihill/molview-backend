const fs = require("fs");
const path = require("path");
const axios = require("axios");

const directoryPath = "../../src/main/resources/pdb";
const graphqlEndpoint = "https://molview.snazzyrobot.com/graphql";
const authToken = "my_JWT_token";

function findCompound(data) {
  const compound = data.split(/\r?\n/).find(line => line.includes("COMPND"));
  return compound?.slice(10).trim() || "";
}

async function postData(fileName, data) {
  const mutation = `
    mutation createPdbData($input: CreatePdbDataInput!) {
      createPdbData(input: $input) {
        id
        name
        data
        compound
      }
    }
  `;

  const variables = {
    input: {
      name: fileName,
      data: data,
      compound: findCompound(data),
    },
  };

  try {
    const response = await axios.post(graphqlEndpoint, {
      query: mutation,
      variables: variables,
    }, {
      headers: {
        'Authorization': `Bearer ${authToken}`, // Add your authentication token here
      },
    });

    console.log("GraphQL Mutation Response:", response.status);
  } catch (error) {
    console.error("Error posting data to GraphQL endpoint:", error.message);
  }
}

function readDirectoryRecursively(directoryPath) {
  fs.readdir(directoryPath, {withFileTypes: true}, (err, files) => {
    if (err) {
      return console.error("Unable to scan directory:", err);
    }

    files.forEach(file => {
      const filePath = path.join(directoryPath, file.name);

      if (file.isDirectory()) {
        // If the file is a directory, call the function recursively
        readDirectoryRecursively(filePath);
      } else {
        // Otherwise, read and process the file
        fs.readFile(filePath, "utf8", (err, data) => {
          if (err) {
            return console.error("Error reading file:", filePath, err.message);
          }
          postData(file.name, data);
        });
      }
    });
  });
}

// Initial call to start reading from the specified directory
readDirectoryRecursively(directoryPath);
