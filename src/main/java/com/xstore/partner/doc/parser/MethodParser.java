package com.xstore.partner.doc.parser;

import com.xstore.partner.doc.domain.MethodInfo;

import java.util.List;

public interface MethodParser {

    MethodInfo parse(String methodId);

}
