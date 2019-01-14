/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { ProductRecordUpdateComponent } from 'app/entities/product-record/product-record-update.component';
import { ProductRecordService } from 'app/entities/product-record/product-record.service';
import { ProductRecord } from 'app/shared/model/product-record.model';

describe('Component Tests', () => {
    describe('ProductRecord Management Update Component', () => {
        let comp: ProductRecordUpdateComponent;
        let fixture: ComponentFixture<ProductRecordUpdateComponent>;
        let service: ProductRecordService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [ProductRecordUpdateComponent]
            })
                .overrideTemplate(ProductRecordUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProductRecordUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProductRecordService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ProductRecord(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.productRecord = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new ProductRecord();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.productRecord = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
