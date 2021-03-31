package com.github.thanhvoquang.mvccore.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class BaseDTO<ID> {

    private ID id;
}

