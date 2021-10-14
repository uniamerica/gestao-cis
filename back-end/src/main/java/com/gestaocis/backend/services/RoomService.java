package com.gestaocis.backend.services;

import com.gestaocis.backend.DTOs.RoomDTOs.NewRoomRequestDTO;
import com.gestaocis.backend.DTOs.RoomDTOs.RoomResponseDTO;
import com.gestaocis.backend.enums.Role;
import com.gestaocis.backend.enums.RoleEntity;
import com.gestaocis.backend.exceptions.BadRequestException;
import com.gestaocis.backend.models.Room;
import com.gestaocis.backend.models.User;
import com.gestaocis.backend.repositories.HealthInsuranceRepository;
import com.gestaocis.backend.repositories.RoleEntityRepository;
import com.gestaocis.backend.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepository userRepository;

    @Autowired
    private RoleEntityRepository roleEntityRepository;

    @Autowired
    private HealthInsuranceRepository healthInsuranceRepository;
    private Room UUID;

    // SAVES NEW ROOM
    public RoomResponseDTO save(NewRoomRequestDTO room) throws Exception {

        RoleEntity role = roleEntityRepository
                .findByRoleName(Role.ROLE_ROOM)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role not found"));

     // LIST (supposedly kkk)
        public List<RoomResponseDTO> findAll(){
            try{
                role = this.roleEntityRepository
                        .findByRoleName(Role.ROLE_ROOM)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Role Not Found"));
                List<User> Room = this.userRepository.findByRole(role);
                return Room.stream().map(RoomResponseDTO::new).collect(Collectors.toList());
            }catch (Exception exception){
                throw new BadRequestException(exception.getMessage());
            }
        }

        // achados por UUID
        public RoomResponseDTO findByUUID(UUID uuid){
            try{
                User Room = this.userRepository.findByUuid(uuid)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room not Found, please check again"));
                return new RoomResponseDTO(room);
            }catch (Exception exception){
                throw new BadRequestException(exception.getMessage());

                try {
                    User secretaryUser = User
                            .builder()
                            .uuid(new UUID(1L, 2L))

                    return new RoomResponseDTO(RoomRepository.save(secretaryUser));
                }catch (Exception exception){
                    throw new BadRequestException(exception.getMessage());
                }
            }

        //DELETE (finding by UUID)
        public boolean delete(UUID uuid){
            try{
                User room = this.userRepository.findByUuid(uuid)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room not Found, please check again"));
                this.userRepository.delete(room);

                if(userRepository.findByUuid(uuid).isEmpty()) {
                    return true;
                }else{
                    return false;
                }

            }catch (Exception exception){
                throw new BadRequestException(exception.getMessage());
            }
        }

    }
