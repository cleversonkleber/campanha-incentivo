# Changelog
Todas as mudanças notáveis para este projeto serão documentadas neste arquivo.

## [1.0.0] - 2025-07-29

### Changed
- **Refatorado `ParticipanteService` e `CampanhaService`** para utilizar injeção de dependência por construtor. Isso melhora a testabilidade e a adesão às boas práticas de design.
- **Adicionado `@Mapping` explícito ao `ParticipanteMapper`** para garantir que o mapeamento entre a entidade e o DTO seja claro e robusto.

### Added
- **Adicionado o método `findByNome` no `CampanhaRepository`** para permitir a busca de campanhas por nome.

### Fixed
- **Corrigido construtor com parâmetros incorretos na classe `Campanha`**, garantindo que a inicialização do objeto seja feita de forma correta.
