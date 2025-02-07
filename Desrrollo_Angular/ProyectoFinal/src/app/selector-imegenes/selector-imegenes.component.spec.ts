import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectorImegenesComponent } from './selector-imegenes.component';

describe('SelectorImegenesComponent', () => {
  let component: SelectorImegenesComponent;
  let fixture: ComponentFixture<SelectorImegenesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SelectorImegenesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SelectorImegenesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
