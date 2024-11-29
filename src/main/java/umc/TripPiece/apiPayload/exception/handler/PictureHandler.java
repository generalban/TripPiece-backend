package umc.TripPiece.apiPayload.exception.handler;

import umc.TripPiece.apiPayload.code.BaseErrorCode;
import umc.TripPiece.apiPayload.exception.GeneralException;

public class PictureHandler extends GeneralException {
    public PictureHandler(BaseErrorCode errorCode) {super(errorCode);}
}
