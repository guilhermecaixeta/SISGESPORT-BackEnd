import { AlunoModule } from './aluno.module';

describe('AlunoModule', () => {
  let alunoModule: AlunoModule;

  beforeEach(() => {
    alunoModule = new AlunoModule();
  });

  it('should create an instance', () => {
    expect(alunoModule).toBeTruthy();
  });
});
