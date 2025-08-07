package com.flic.courseRegister.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AttachmentType {
    @JsonProperty("4x6_photo") _4x6_photo,
    @JsonProperty("3x4_photo") _3x4_photo,
    @JsonProperty("birth_certificate") birth_certificate
}
