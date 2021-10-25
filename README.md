# Anonymizer

This is a simple web service that allows you to mask the next types of sensitive data:
- **emails**, by replacing username and domain part of email with random values (`adam.smith@example.com -> economist@email.net`);
- **URLs**, by replacing the domain names, and saving the actual nested path inside domain (`https://music.com/artists/adele/tracks/skyfall` -> `https://some.domain/artists/adele/tracks/skyfall`);
- **IDs**, by replacing sequence of three or more digits with random numeric sequence same length (`12345 -> 98644`); 

Worth to mention that __email addresses masked by segments__ (username and domain name) to preserve relation between addresses that belong to one domain, e.g. corporate emails.
For example, `adam.smith@institution.org` and `isaac.newton@institution.org` might be masked as the next email addresses: `economist@place.net` and `physicist@place.net`, preserving the domain of email consistent

## Installation

Command bellow launches application locally on port 8080 by default:

```bash
./gradlew run
```

## Testing

Command below run all tests:

```bash
./gradlew test
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
