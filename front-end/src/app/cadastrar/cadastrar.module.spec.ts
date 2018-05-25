import { CadastrarModule } from './cadastrar.module';

describe('CadastrarModule', () => {
  let cadastroModule: CadastrarModule;

  beforeEach(() => {
    cadastroModule = new CadastrarModule();
  });

  it('should create an instance', () => {
    expect(cadastroModule).toBeTruthy();
  });
});
