import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlunoEtapa1Component } from './aluno-etapa1.component';

describe('AlunoEtapa1Component', () => {
  let component: AlunoEtapa1Component;
  let fixture: ComponentFixture<AlunoEtapa1Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlunoEtapa1Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlunoEtapa1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
