import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryDataTableComponent } from './category-data-table.component';

describe('CategoryDataTableComponent', () => {
  let component: CategoryDataTableComponent;
  let fixture: ComponentFixture<CategoryDataTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CategoryDataTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CategoryDataTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
