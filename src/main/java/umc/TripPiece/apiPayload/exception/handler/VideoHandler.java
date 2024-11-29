package umc.TripPiece.apiPayload.exception.handler;

import umc.TripPiece.apiPayload.code.BaseErrorCode;
import umc.TripPiece.apiPayload.exception.GeneralException;

public class VideoHandler extends GeneralException {
    public VideoHandler(BaseErrorCode errorCode) {super(errorCode);}
}
