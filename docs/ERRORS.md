## Errors

To ensure a smooth experience, here are some common error codes you may encounter:

| Error Code | Description                                        |
|------------|----------------------------------------------------|
| `422`      | Bad Request: The request is invalid or malformed.  |

When you encounter an error, the response will include a message detailing the issue along with the error code.

```json
{
    "message": "Must enter at least one topping",
    "errorCode": 422
}
```
