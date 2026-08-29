## [unreleased]

### 📚 Documentation

- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md
- *(changelog)* Update CHANGELOG.md

### ⚙️ Miscellaneous Tasks

- *(docs)* Update dependency version in README to 4.0.4
- *(lib)* Add newline after license headers and adjust Maven formatting settings
- *(repo)* Remove .gitattributes file
- *(workflows)* Update changelog push command to use ci.skip flag and change commit message
## [4.0.4] - 2026-08-29

### ⚙️ Miscellaneous Tasks

- *(lib)* Remove Spotbugs plugin from maven lifecycle
- *(lib)* Remove Spotbugs plugin from maven lifecycle
- *(docs)* Update dependency version in README to 4.0.3
- *(workflows)* Add bash language hint for test step
- *(docs)* Add links to precondition libraries in README
- *(workflows)* Add changelog generation workflow and update release job configuration
- *(workflows)* Add skip ci flag to changelog commit message
- *(docs)* Refine wording in RELEASE.md for release version information
- *(examples)* Migrate examples to new structure and add comprehensive tests
- *(lib)* Add license headers to source and test files, configure license-maven-plugin
## [4.0.3] - 2026-08-02

### ⚙️ Miscellaneous Tasks

- *(docs)* Bump artifact version in README to 4.0.2
- *(workflows)* Set `fetch-depth` to 0 in MegaLinter step
- *(workflows)* Add reference for JaCoCo Report action in test workflow
- *(format)* Add spotless formatting
- *(lib)* Add `project.reporting.outputEncoding` property in pom.xml for UTF-8 reporting
- *(lib)* Add SpotBugs Maven plugin with fail-on-error configuration
- *(mise)* Update `mvn:validate` to `mvn:compile` task and adjust pre-commit hook configuration
- *(lib)* Extract Javadoc plugin version to a property in pom.xml
- *(format)* Standardize quotes, fix Markdown links, and adjust MegaLinter config
- *(format)* Comment out Eclipse formatter settings in pom.xml
- *(workflows)* Remove path filtering for pull request events in MegaLinter config
- *(test)* More test coverage (#144)
- *(lib)* Include `pom.xml` in Spotless formatting configuration
- *(lib)* Remove Spotless plugin from maven lifecycle
## [4.0.2] - 2026-06-03

### 🚀 Features

- *(lib)* Add `containsKey` and `containsValue` methods with validation for maps (#116)

### 📚 Documentation

- *(lib)* Replace regex validation with specialized Ensure methods and update documentation (#115)

### ⚙️ Miscellaneous Tasks

- *(docs)* Bump artifact version in README to 4.0.1
- *(docs)* Update .gitignore
- *(workflows)* Megalinter config
- *(workflows)* Update GitHub Actions versions with "v" prefix for consistency
- *(docs)* Remove redundant code comments from guidelines.md
- *(workflows)* Set `continue-on-error` to false for MegaLinter step
- *(workflows)* Set `continue-on-error` to false for MegaLinter step
- *(lib)* Revert artifact version to 4.0.2-SNAPSHOT in pom.xml
- *(lib)* Revert artifact version to 4.0.2-SNAPSHOT in pom.xml
## [ensure4j-4.0.1] - 2026-05-17

### ⚙️ Miscellaneous Tasks

- *(workflow)* Refine GitHub workflows with improved permissions and environment handling
- *(workflow)* Upgrade action versions and integrate zizmor pre-commit hook
- *(pom)* Upgrade ensure4j dependency to version 4.0.0
- *(examples)* Rename overloaded `placeOrder` methods for clarity in README examples
- *(javadocs)* Add validation usage examples for `Ensure` methods and update library documentation (#105)
- *(tasks)* Update Maven validate task description and simplify command
- *(examples)* Add inline comment in `JavaStreamExample` for clarity
- *(core)* Add pattern-based validation for alphanumeric and email strings (#106)
- *(docs)* Update `withdraw` method example to include balance parameter in Javadoc
- *(tests)* Add pattern-based string matching tests and update method expectations (#107)
- *(docs)* Enhance README with postconditions explanation and updated library usage details
- *(examples)* Update `JavaStreamExample` and add `RecordCompactConstructorExample` for better ensure4j usage demonstration
- *(examples)* Update `JavaStreamExample` and add `RecordCompactConstructorExample` for better ensure4j usage demonstration
- *(dependabot)* Add configuration for GitHub Actions dependency updates
- *(dependabot)* Add configuration for pip dependency updates
- *(pre-commit)* Add Actionlint hook and update existing hook names
- *(dependabot)* Add configuration for pre-commit dependency updates
- *(workflows)* Set `persist-credentials` to true in release workflow
- *(workflows)* Update tag output handling in release workflow
- *(lib)* Update Javadoc for `EnsureException` and revert project version to 4.0.1-SNAPSHOT
## [ensure4j-4.0.0] - 2026-05-16

### 📚 Documentation

- *(README)* Bump version to 3.0.5
- *(readme)* Add Maven Central and Maven Test badges
- *(contributing)* Fix typo in local development setup description

### ⚙️ Miscellaneous Tasks

- *(release)* Fix repository key in release workflow
- *(release)* Enhance release pipeline with multi-stage workflows
- *(release)* Rename workflow to 'Release' in release.yml
- *(release)* Remove redundant comment in release workflow
- *(release)* Remove unused permissions block from release workflow
- *(lib)* Bump Ensure4j version to 4.0.0-SNAPSHOT in pom.xml (#103)
- *(release)* Add version_type input to workflow and fix typo in pom.xml description
- *(release)* Update Maven commands with enhanced args for consistency and color output
- *(workflow)* Rename workflow to 'Maven Test' and improve Maven command arguments
- *(pom)* Add projectVersionPolicyId to Maven Release Plugin configuration
- *(pom)* Update projectVersionPolicyId to SemVerMinorDevelopment in Maven Release Plugin configuration
- *(pom)* Remove projectVersionPolicyId from Maven Release Plugin configuration
- *(workflow)* Add Maven version type handling to release workflow
- *(workflow)* Update release workflow to use proper SemVer release types
- *(workflow)* Trunk based semantic release workflow
- *(workflow)* Update mega-linter path matching for PR triggers
- *(lib)* 4.0.0 (#104)
- *(workflow)* Rename test workflow and update README badges
## [ensure4j-3.0.5] - 2026-05-14

### 🚀 Features

- *(objectops)* Add `isDeepEquals` (#99)
- *(arrayops)* Add array equality and deep equality validation methods (#102)

### 📚 Documentation

- *(README)* Bump Ensure4j version to 3.0.3 in README dependencies example
- *(ci)* Add descriptive comments to actions and update links (#80)
- *(guidelines)* Update Java version info and improve testing/coding guidelines (#83)
- *(README:GUIDELINES)* Improve task syntax, guidelines, and pre-commit integration (#98)

### ⚙️ Miscellaneous Tasks

- *(pre-commit)* Update dependencies, add regex validation, and enhance CI setup (#76)
- *(release)* Add prepareVerifyArgs to Maven release plugin configu… (#77)
- *(ci)* Add DCO verification workflow (#79)
- *(ci)* Enhance category mapping in changelog generation (#82)
- *(refactor)* Replace custom `isNull` utility and deprecate unused Ensure APIs (#84)
- *(docs)* Mark deprecated methods in EnsureStringOps and EnsureNullOps (#85)
- *(ci)* Remove dco github action (#86)
- *(ci)* Add dco2 app config (#87)
- *(ci)* Add megalinter (#88)
- *(lint)* Fix some lint issues (#89)
- *(dependabot)* Update dependency schedule to weekly for examples directory (#90)
- *(dev)* Update workflows for path filtering, Git config, and Maven execution (#92)
- *(build)* Update Maven plugins, GitHub Actions, and linters config (#93)
- *(ci)* Add workflow to label PRs with release version (#95)
- *(ci)* Automate label creation with dynamic version extraction from pom.xml (#96)
- *(ci)* Fix release tagging by using environment variables (#97)
- *(lib)* Bump Ensure4j version to 3.0.5-SNAPSHOT in pom.xml
- *(release)* Pass PAT_TOKEN secret to release workflow
- *(release)* Pass token for Git user in release workflow
- *(release)* Pass token for Git user in release workflow
## [ensure4j-3.0.3] - 2026-05-11

### 🚀 Features

- Improve documentation and refactor Maven properties (#65)
- Enforce Java 17 & Maven 3.8+ compatibility, add backward compatibility check (#69)
- Add EnsureDateTimeOps utility with support for future and past/present validation (#71)
- Add regex-based string validation utility to EnsureStringOps (#72)
- Add EnsureDateTimeOps support

### 🚜 Refactor

- Remove Maven Wrapper and add pre-commit hooks
- Improve and consolidate validation methods (#64)
- Remove architecture tests and deprecated methods in EnsureNullOps (#68)
- Simplify getPublicMethodCount implementation and clean up tests
- Simplify Java versioning and update dependency schedules
- *(examples)* Consolidate validation examples and update structure
- *(examples)* Update package structure and comment out regex validation

### 📚 Documentation

- Remove redundant pull request guidelines from CONTRIBUTING.md (#59)
- Add development guidelines to CONTRIBUTING.md (#60)
- Add missing @return tags to Ensure operations Javadoc (#70)
- Update package-level documentation with new validations and annotations (#75)

### ⚡ Performance

- Enhance code contracts with @Contract annotations for parameter validation (#74)

### ⚙️ Miscellaneous Tasks

- Add GitHub Actions workflow for testing on pull requests
- Update release workflow categories for changelog grouping (#66)
- Update GitHub Actions workflows to use latest action versions (#67)
## [ensure4j-0.0.1] - 2025-11-17
