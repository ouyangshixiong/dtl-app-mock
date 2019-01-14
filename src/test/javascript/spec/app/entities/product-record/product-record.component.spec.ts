/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DtlAppMockTestModule } from '../../../test.module';
import { ProductRecordComponent } from 'app/entities/product-record/product-record.component';
import { ProductRecordService } from 'app/entities/product-record/product-record.service';
import { ProductRecord } from 'app/shared/model/product-record.model';

describe('Component Tests', () => {
    describe('ProductRecord Management Component', () => {
        let comp: ProductRecordComponent;
        let fixture: ComponentFixture<ProductRecordComponent>;
        let service: ProductRecordService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [ProductRecordComponent],
                providers: []
            })
                .overrideTemplate(ProductRecordComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(ProductRecordComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ProductRecordService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new ProductRecord(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.productRecords[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
