import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImageShowComponent } from './image-show.component';

describe('ImageShowComponent', () => {
  let component: ImageShowComponent;
  let fixture: ComponentFixture<ImageShowComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ImageShowComponent]
    });
    fixture = TestBed.createComponent(ImageShowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
