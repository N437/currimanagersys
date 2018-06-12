package com.winter.services.impl;

import com.winter.model.resource;
import com.winter.services.ResourceService;
import org.springframework.stereotype.Service;

@Service(value = "ResourceService")
public class ResourceServiceimpl extends BaseServiceimpl<resource> implements ResourceService {
}
