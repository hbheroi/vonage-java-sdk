package com.nexmo.client.voice;
/*
 * Copyright (c) 2011-2016 Nexmo Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nexmo.client.NexmoUnexpectedException;

// TODO: Re-insert 'rate' and 'price' (currently being ignored)
// TODO: Convert direction and status to enum values
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "_links", "rate", "price", "duration", "start_time", "end_time" })
public class CallRecord {
    private Endpoint to;
    private Endpoint from;
    private String answerUrl;

    private String answerMethod = "GET";
    private String eventUrl = null;
    private String eventMethod = null;
    private String machineDetection = null;
    private String status = null;
    private String direction = null;
    private Integer lengthTimer = null;
    private Integer ringingTimer = null;
    private Integer duration = null;
    private String network = null;

    private String callId = null;
    private String conversationId = null;

    public CallRecord() {}

    public CallRecord(String to, String from, String answerUrl) {
        this(new Endpoint(to), new Endpoint(from), answerUrl);
    }

    public CallRecord(Endpoint to, Endpoint from, String answerUrl) {
        this.to = to;
        this.from = from;
        this.answerUrl = answerUrl;
    }

    public Endpoint getTo() {
        return to;
    }

    public void setTo(Endpoint to) {
        this.to = to;
    }

    public Endpoint getFrom() {
        return from;
    }

    public void setFrom(Endpoint from) {
        this.from = from;
    }

    @JsonProperty("answer_url")
    public String[] getAnswerUrl() {
        return new String[]{answerUrl};
    }

    public void setAnswerUrl(String answerUrl) {
        this.answerUrl = answerUrl;
    }

    @JsonProperty("answer_method")
    public String getAnswerMethod() {
        return answerMethod;
    }

    public void setAnswerMethod(String answerMethod) {
        this.answerMethod = answerMethod;
    }

    @JsonProperty("event_url")
    public String getEventUrl() {
        return eventUrl;
    }

    public void setEventUrl(String eventUrl) {
        this.eventUrl = eventUrl;
    }

    @JsonProperty("event_method")
    public String getEventMethod() {
        return eventMethod;
    }

    public void setEventMethod(String eventMethod) {
        this.eventMethod = eventMethod;
    }

    @JsonProperty("machine_detection")
    public String getMachineDetection() {
        return machineDetection;
    }

    // TODO: This should be an enum param:
    public void setMachineDetection(String machineDetection) {
        this.machineDetection = machineDetection;
    }

    @JsonProperty("length_timer")
    public Integer getLengthTimer() {
        return lengthTimer;
    }

    public void setLengthTimer(Integer lengthTimer) {
        this.lengthTimer = lengthTimer;
    }

    @JsonProperty("ringing_timer")
    public Integer getRingingTimer() {
        return ringingTimer;
    }

    public void setRingingTimer(Integer ringingTimer) {
        this.ringingTimer = ringingTimer;
    }

    @JsonProperty("uuid")
    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    @JsonProperty("conversation_uuid")
    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String toJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException jpe) {
            throw new NexmoUnexpectedException("Failed to produce json from Call object.", jpe);
        }
    }

    public String toString() {
        return new StringBuilder()
                .append("<CallRecord ")
                .append("ID: ").append(this.getCallId()).append(", ")
                .append("From: ").append(this.getFrom().getNumber()).append(", ")
                .append("To: ").append(this.getTo().getNumber()).append(", ")
                .append("Status: ").append(this.getStatus())
                .append(">")
                .toString();
    }
}