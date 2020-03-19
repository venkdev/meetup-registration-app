package com.k15t.pat.dto;

import java.util.List;

public class UsersResponseDto extends BaseResponseDto{

    List<UserDto> userDtoList;

    public List<UserDto> getUserDtoList() {
        return userDtoList;
    }

    public void setUserDtoList(List<UserDto> userDtoList) {
        this.userDtoList = userDtoList;
    }
}
