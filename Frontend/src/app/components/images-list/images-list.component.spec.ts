import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImagesListComponent } from './images-list.component';

describe('ImagesListComponent', () => {
  let component: ImagesListComponent;
  let fixture: ComponentFixture<ImagesListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ImagesListComponent]
    });
    fixture = TestBed.createComponent(ImagesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
