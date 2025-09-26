package dynamodb;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;

public class DynamoDBUtil {
    private final DynamoDbClient dynamoDbClient;
    private final String tableName = "LeaveRequests";

    public DynamoDBUtil() {
        // Set your AWS region here
        this.dynamoDbClient = DynamoDbClient.builder()
                .region(Region.US_EAST_1) // Change to your region
                .build();
    }

    public void saveLeaveRequest(LeaveRequest req) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("requestId", AttributeValue.builder().s(req.getRequestId()).build());
        item.put("employeeId", AttributeValue.builder().n(String.valueOf(req.getEmployeeId())).build());
        item.put("fromDate", AttributeValue.builder().s(req.getFromDate()).build());
        item.put("toDate", AttributeValue.builder().s(req.getToDate()).build());
        item.put("status", AttributeValue.builder().s(req.getStatus()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(item)
                .build();

        dynamoDbClient.putItem(request);
        System.out.println("Saved leave request to DynamoDB: " + req.getRequestId());
    }
}
