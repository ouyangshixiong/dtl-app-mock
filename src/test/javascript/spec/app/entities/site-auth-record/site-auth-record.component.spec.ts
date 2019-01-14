/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DtlAppMockTestModule } from '../../../test.module';
import { SiteAuthRecordComponent } from 'app/entities/site-auth-record/site-auth-record.component';
import { SiteAuthRecordService } from 'app/entities/site-auth-record/site-auth-record.service';
import { SiteAuthRecord } from 'app/shared/model/site-auth-record.model';

describe('Component Tests', () => {
    describe('SiteAuthRecord Management Component', () => {
        let comp: SiteAuthRecordComponent;
        let fixture: ComponentFixture<SiteAuthRecordComponent>;
        let service: SiteAuthRecordService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [SiteAuthRecordComponent],
                providers: []
            })
                .overrideTemplate(SiteAuthRecordComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SiteAuthRecordComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SiteAuthRecordService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SiteAuthRecord(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.siteAuthRecords[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
