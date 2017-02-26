
package com.example.esraa.drrobot;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import org.parceler.Parcel;

@org.parceler.Parcel(Parcel.Serialization.BEAN)
@com.bluelinelabs.logansquare.annotation.JsonObject(fieldNamingPolicy = JsonObject.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
public class Message {
    @JsonField
    private String message;
    @JsonField
    private String uuid;

    /**
     * 
     * @return
     *     The message
     */

    public  Message(String message,String uuid){
        this.message=message;
        this.uuid=uuid;

    }
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 
     * @return
     *     The uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 
     * @param uuid
     *     The uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
