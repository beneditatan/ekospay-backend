## Functional Requirements

See the [README](../README.md) for project context and the first milestone's objective.

| ID | Requirement | Details | Status |
| --- | --- | --- | --- |
| FR-001 | Create an owner profile | Via `POST /api/v1/owners`. Require a full name, email address, and phone number. Generate an internal UUID, validate the email and enforce case-insensitive uniqueness, and store the phone number in international format. | Not implemented |
| FR-002 | Register a property | Via `POST /api/v1/owners/{ownerId}/properties`. An existing owner can register multiple properties. Require a name, street address, province, city or regency, district, village or subdistrict, and postal code. The property phone number is optional. Default to `BOARDING_HOUSE`, `Asia/Jakarta`, and `IDR`. | Not implemented |
| FR-003 | Add a room | Via `POST /api/v1/properties/{propertyId}/rooms`. Add one room at a time to an existing property. Require a room label unique within that property and a positive monthly rent in whole rupiah. Floor and description are optional. New rooms start as `VACANT`. | Not implemented |
| FR-004 | Check onboarding progress | Via `GET /api/v1/owners/{ownerId}/onboarding`. Return `PROPERTY_REQUIRED` when the owner has no property, `ROOM_REQUIRED` when none of their properties has a room, and `COMPLETE` when at least one does. Save records so progress survives an application restart. | Not implemented |
| FR-005 | Return consistent responses. | Use `201` for successful creation, `200` for status retrieval, `400` for invalid input, `404` for a missing owner or property, and `409` for duplicate emails or room labels. | Not implemented |
