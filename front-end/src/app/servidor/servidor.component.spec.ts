import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ServidorComponent } from './servidor.component';

describe('ServidorComponent', () => {
  let component: ServidorComponent;
  let fixture: ComponentFixture<ServidorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ServidorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ServidorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
