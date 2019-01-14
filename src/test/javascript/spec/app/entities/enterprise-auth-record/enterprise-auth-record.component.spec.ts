/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DtlAppMockTestModule } from '../../../test.module';
import { EnterpriseAuthRecordComponent } from 'app/entities/enterprise-auth-record/enterprise-auth-record.component';
import { EnterpriseAuthRecordService } from 'app/entities/enterprise-auth-record/enterprise-auth-record.service';
import { EnterpriseAuthRecord } from 'app/shared/model/enterprise-auth-record.model';

describe('Component Tests', () => {
    describe('EnterpriseAuthRecord Management Component', () => {
        let comp: EnterpriseAuthRecordComponent;
        let fixture: ComponentFixture<EnterpriseAuthRecordComponent>;
        let service: EnterpriseAuthRecordService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [EnterpriseAuthRecordComponent],
                providers: []
            })
                .overrideTemplate(EnterpriseAuthRecordComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(EnterpriseAuthRecordComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(EnterpriseAuthRecordService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new EnterpriseAuthRecord(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.enterpriseAuthRecords[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
