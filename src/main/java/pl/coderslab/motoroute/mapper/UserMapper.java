package pl.coderslab.motoroute.mapper;

import org.mapstruct.Mapper;
import pl.coderslab.motoroute.dto.UserDetailsDto;
import pl.coderslab.motoroute.dto.UserEditDto;
import pl.coderslab.motoroute.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEditDto userToUserEditDto(User user);
    UserDetailsDto userToUserDetailsDto(User user);
}
