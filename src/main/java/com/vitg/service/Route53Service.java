package com.vitg.service;

import com.vitg.dto.ResponseDTO;

public interface Route53Service {

	ResponseDTO createSubDomain(String subDomain);
}
