package com.crazyemperor.construction_management.bank.model;

import java.time.LocalDateTime;

public record BankResponse(LocalDateTime date, boolean success) {
}
