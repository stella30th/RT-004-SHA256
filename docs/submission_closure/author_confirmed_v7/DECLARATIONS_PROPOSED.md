# Proposed manuscript declarations — author-reported, not independent certification

## AI model use / evaluated systems
The study used OpenAI GPT-6 Sol and Google Gemini 3.8 Flash through their respective consumer web interfaces for generation of JUnit test suites. These model labels, the interface type, the reported existence of original generation logs/exports, and the claimed protocol freeze are provided by the authorized project representative on 11 November 2026. The exact model versions at generation time, per-call timestamps, settings, and generation contexts require reconciliation to archived session-level evidence. A provider's published API model identifier is not proof of which model snapshot served a historical consumer-web session.

The research representative reports that AI was used exclusively to generate test suites during the original study, not for manually diagnosing failures, developing case-specific focus statements, or authoring the original manuscript. This is a declaration of the study's historical workflow and must be checked against the complete team records and individual authors' confirmations. It is **not a declaration about the current revised manuscript**: this revision package was drafted with AI assistance, and text incorporated from it must be acknowledged and cited in relevant sections as required by the venue policy.

## Protocol and deviations
The authorized project representative reports that a protocol freeze was documented by a commit or other time-stamped record. The actual commit identifier, approval evidence, protocol content, and chronology relative to the first model-generation session are not yet supplied. Accordingly, do not label the study preregistered or prospectively frozen until the evidence is checked. `PROTOCOL_FROZEN_DRAFT.md` itself explicitly carries DRAFT status. Record the freeze candidate and deviations in `EVIDENCE_INTAKE.md`.

## Funding — proposed wording subject to all-author confirmation
The authors report that this work received no external research funding, sponsored computational credits, or other third-party support. This should be included in the submitted manuscript only after verification that the reporting lead was authorized to certify this statement for all four authors and that each coauthor approved the exact statement.

## Competing interests — proposed wording subject to all-author confirmation
The authors report no financial or nonfinancial competing interests. Individual author confirmations and record dates must be retained privately, or an auditable group-authorization record established.

## Data and artifact availability — NOT YET A PUBLISHED RELEASE
The reproduction materials are being prepared for double-anonymous peer review. A complete, third-party-accessible anonymous archive URL, immutable revision, redistribution/license review, and reviewer access test have not yet been supplied. The submitted paper must not claim an artifact is available until a usable link has been verified. The current official repository is a team-identified development repository and must not be used as the anonymous submission artifact without an appropriate anonymization process.

## Internal author identities (never include in anonymous draft)
Names and any approvals belong in `private_approvals_DO_NOT_COMMIT/`, not in the submitted PDF, archive, or repository evidence intended for blind review.

## Required citations and policy
- ICST 2027 NIER CFP: https://conf.researchr.org/track/icst-2027/icst-2027-nier
- OpenAI model documentation: https://developers.openai.com/api/docs/models/gpt-6-sol
- Google model documentation: https://ai.google.dev/gemini-api/docs/models/gemini-3.8-flash
