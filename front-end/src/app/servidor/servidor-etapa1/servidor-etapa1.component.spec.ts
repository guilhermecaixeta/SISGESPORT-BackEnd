import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServidorEtapa1Component } from './servidor-etapa1.component';

describe('ServidorEtapa1Component', () => {
  let component: ServidorEtapa1Component;
  let fixture: ComponentFixture<ServidorEtapa1Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServidorEtapa1Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServidorEtapa1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
