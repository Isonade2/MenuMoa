package com.wku.menumoa.dto.menu;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateMenuDTO {
    private Long storeId;
    private List<MenuDTO> menus;
}
