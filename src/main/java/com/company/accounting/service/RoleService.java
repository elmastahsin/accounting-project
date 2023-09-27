package com.company.accounting.service;


import com.company.accounting.dto.RoleDTO;
import com.company.accounting.service.common.CrudService;

import java.util.List;


public interface RoleService extends CrudService<RoleDTO, Long> {

    List<RoleDTO> getRolesForCurrentUser();
}
