package umc.TripPiece.apiPayload.exception.handler;

import umc.TripPiece.apiPayload.code.BaseErrorCode;
import umc.TripPiece.apiPayload.exception.GeneralException;

public class NotFoundHandler extends GeneralException {
    public NotFoundHandler(BaseErrorCode errorCode) {super(errorCode);}
}
