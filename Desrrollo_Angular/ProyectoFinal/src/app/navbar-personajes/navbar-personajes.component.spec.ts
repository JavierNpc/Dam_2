import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarPersonajesComponent } from './navbar-personajes.component';

describe('NavbarPersonajesComponent', () => {
  let component: NavbarPersonajesComponent;
  let fixture: ComponentFixture<NavbarPersonajesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NavbarPersonajesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NavbarPersonajesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
