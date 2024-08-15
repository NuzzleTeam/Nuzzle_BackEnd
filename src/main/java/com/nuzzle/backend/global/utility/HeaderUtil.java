package com.nuzzle.backend.global.utility;

import com.nuzzle.backend.global.exception.CommonException;
import com.nuzzle.backend.global.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

import java.util.Optional;


public class HeaderUtil {
    public static Optional<String> refineHeader(
            HttpServletRequest request,
            String header,
            String prefix) {
        String unpreparedToken = request.getHeader(header);

        if (!StringUtils.hasText(unpreparedToken) || !unpreparedToken.startsWith(prefix)) {
            throw new CommonException(ErrorCode.INVALID_HEADER_ERROR);
        }

        return Optional.of(unpreparedToken.substring(prefix.length()));
    }
}