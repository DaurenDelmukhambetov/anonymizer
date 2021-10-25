# Anonymizer

Simple web service to mask sensitive data in text

## Installation

Command bellow launches application locally on port 8080 by default:

```bash
./gradlew run
```

## Usage

Send a request to `/anonymize` endpoint with a text payload to anonymize:

```bash
curl -X POST \
  http://localhost:8080/anonymize \
  -H 'content-type: application/json' \
  -d '{
	"content": "Hello world! I am writing to you from my@email.com"
}'
```

You will get a response with masked sensitive data, such as email, numbers, and URLs:

```bash
{
    "content": "Hello world! I am writing to you from kirby.gusikowski@franecki.io"
}
```

## Customization

By default, masking works by replacing sensitive data with random value.
You can add you own strategy for masking data by implementing `DataTransformationProcessor`.
