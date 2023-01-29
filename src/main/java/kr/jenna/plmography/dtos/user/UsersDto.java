package kr.jenna.plmography.dtos.User;

import java.util.List;

public class UsersDto {
    private List<UserDto> users;

    public UsersDto(List<UserDto> users) {
        this.users = users;
    }

    public List<UserDto> getUsers() {
        return users;
    }
}
