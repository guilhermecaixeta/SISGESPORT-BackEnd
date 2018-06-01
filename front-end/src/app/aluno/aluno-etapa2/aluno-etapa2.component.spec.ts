import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AlunoEtapa2Component } from './aluno-etapa2.component';

describe('AlunoEtapa2Component', () => {
  let component: AlunoEtapa2Component;
  let fixture: ComponentFixture<AlunoEtapa2Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AlunoEtapa2Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AlunoEtapa2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
