package org.dmiit3iy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dmiit3iy.model.Message;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MsgEvent {


    private String login;

    private Message message;





}