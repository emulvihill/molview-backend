package com.snazzyrobot.molviewbackend.controller;

import lombok.Data;

@Data
public class CreatePdbDataInput {
    String name;
    String compound;
    String data;
}
