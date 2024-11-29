package umc.TripPiece.apiPayload.exception.handler;

import umc.TripPiece.apiPayload.code.BaseErrorCode;
import umc.TripPiece.apiPayload.exception.GeneralException;

public class EmojiHandler extends GeneralException {
    public EmojiHandler(BaseErrorCode errorCode) {super(errorCode);}
}
