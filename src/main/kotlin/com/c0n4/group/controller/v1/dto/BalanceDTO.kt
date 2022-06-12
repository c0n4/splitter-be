package com.c0n4.group.controller.v1.dto

import com.c0n4.user.controller.v1.dto.UserDTO
import java.math.BigDecimal

data class BalanceDTO(val user: UserDTO, val balance: BigDecimal) {}