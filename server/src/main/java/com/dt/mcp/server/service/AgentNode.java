package com.dt.mcp.server.service;

/**
 * Description:<br>
 * Date: 07/09/25-6:01 pm
 *
 * @author ishangarg
 * @since
 */
import org.bsc.langgraph4j.action.NodeAction;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.client.langgraph.multiagent.memory.State;
import org.springframework.ai.tool.ToolCallback;

import java.util.List;
import java.util.Map;

public class AgentNode implements NodeAction<State> {

    String agentName;
    String systemPrompt;
    ToolCallback[] tools;
    ChatClient chatClient;

    public AgentNode(String agentName, String systemPrompt, ToolCallback[] tools, ChatClient chatClient) {
        this.agentName = agentName;
        this.systemPrompt = systemPrompt;
        this.tools = tools;
        this.chatClient = chatClient;
    }

    @Override
    public Map<String, Object> apply(State state) {

        String input = state.getCurrentMessage();

        //Appending previous messages to the context
        StringBuilder inputWithContext = new StringBuilder();
        if (state.getPreviousMessages().size() > 1) {
            inputWithContext.append("PREVIOUS CONVERSATION:\n");
            for (Map<String, String> message : state.getPreviousMessages()) {
                inputWithContext.append(message.get("role")).append(": ").append(message.get("content")).append("\n");
            }
            inputWithContext.append("END OF PREVIOUS CONVERSATION\n\n");
        }

        //Appending the current input
        inputWithContext
                .append(state.getPreviousAgentKey())
                .append(": ")
                .append(input)
                .append("\n");

        //Invoking the Agent
        String response = chatClient.prompt(systemPrompt)
                .toolCallbacks(tools)
                .user(inputWithContext.toString())
                .call()
                .content();

        System.out.println(response);

        //Adding the current message and response to the state
        Map<String, String> newMessage = Map.of(
                "role", agentName,
                "content", response
        );

        List<Map<String, String>> previousMessages = state.getPreviousMessages();
        previousMessages.add(newMessage);

        return Map.of(
                State.CURRENT_MESSAGE_KEY, response,
                State.PREVIOUS_AGENT_KEY, agentName,
                State.PREVIOUS_MESSAGES_KEY, previousMessages
        );

    }

    public String getAgentName() {
        return agentName;
    }
}