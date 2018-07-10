import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroCrudEtapa1Component } from './cadastro-crud-etapa1.component';

describe('AlunoEtapa1Component', () => {
  let component: CadastroCrudEtapa1Component;
  let fixture: ComponentFixture<CadastroCrudEtapa1Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CadastroCrudEtapa1Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastroCrudEtapa1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
