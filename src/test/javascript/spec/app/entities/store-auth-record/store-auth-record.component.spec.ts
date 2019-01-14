/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DtlAppMockTestModule } from '../../../test.module';
import { StoreAuthRecordComponent } from 'app/entities/store-auth-record/store-auth-record.component';
import { StoreAuthRecordService } from 'app/entities/store-auth-record/store-auth-record.service';
import { StoreAuthRecord } from 'app/shared/model/store-auth-record.model';

describe('Component Tests', () => {
    describe('StoreAuthRecord Management Component', () => {
        let comp: StoreAuthRecordComponent;
        let fixture: ComponentFixture<StoreAuthRecordComponent>;
        let service: StoreAuthRecordService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [StoreAuthRecordComponent],
                providers: []
            })
                .overrideTemplate(StoreAuthRecordComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StoreAuthRecordComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StoreAuthRecordService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new StoreAuthRecord(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.storeAuthRecords[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
