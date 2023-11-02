package com.dogsole.developersite.account.dto.vender;


import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class VenderResDTO {

    private Long venderId;

    private String venderEmail;

    private String venderName;

    private String venderPasswd;

    private LocalDateTime createDt;

    private LocalDateTime updateDt;

    private String photo;

    private String photoUrl;
    private String phone;
    private String bNo;
    private String zipcode;
    private String streetAddr;
    private String detailAddr;
    private String extraAddr;

    public VenderTempResDTO orgToTemp(VenderResDTO vender){
        VenderTempResDTO venderTempResDTO = new VenderTempResDTO();
        venderTempResDTO.setEmail(vender.getVenderEmail());
        venderTempResDTO.setName(vender.getVenderName());
        venderTempResDTO.setPhoto(vender.getPhoto());
        venderTempResDTO.setPhone(vender.getPhone());
        venderTempResDTO.setZipcode(vender.getZipcode());
        venderTempResDTO.setStreetAddr(vender.getStreetAddr());
        venderTempResDTO.setDetailAddr(vender.getDetailAddr());
        venderTempResDTO.setExtraAddr(vender.getExtraAddr());

        return venderTempResDTO;
    }
}
