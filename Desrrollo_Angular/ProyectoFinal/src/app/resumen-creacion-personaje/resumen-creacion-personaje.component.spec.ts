import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResumenCreacionPersonajeComponent } from './resumen-creacion-personaje.component';

describe('ResumenCreacionPersonajeComponent', () => {
  let component: ResumenCreacionPersonajeComponent;
  let fixture: ComponentFixture<ResumenCreacionPersonajeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ResumenCreacionPersonajeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ResumenCreacionPersonajeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
