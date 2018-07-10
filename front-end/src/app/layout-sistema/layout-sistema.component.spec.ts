import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LayoutSistemaComponent } from './layout-sistema.component';

describe('LayoutSistemaComponent', () => {
  let component: LayoutSistemaComponent;
  let fixture: ComponentFixture<LayoutSistemaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LayoutSistemaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LayoutSistemaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
