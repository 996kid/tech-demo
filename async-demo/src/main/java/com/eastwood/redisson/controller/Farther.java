package com.eastwood.redisson.controller;

import lombok.Data;

import java.io.Serializable;

@Data
    class Farther implements Serializable {
        Son son;
        String name;
    }