package umc.TripPiece.apiPayload.exception.handler;

import umc.TripPiece.apiPayload.code.BaseErrorCode;
import umc.TripPiece.apiPayload.exception.GeneralException;

public class BadRequestHandler extends GeneralException {
    public BadRequestHandler(BaseErrorCode errorCode) {super(errorCode);}
}
