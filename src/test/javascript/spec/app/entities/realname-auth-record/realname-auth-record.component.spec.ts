/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DtlAppMockTestModule } from '../../../test.module';
import { RealnameAuthRecordComponent } from 'app/entities/realname-auth-record/realname-auth-record.component';
import { RealnameAuthRecordService } from 'app/entities/realname-auth-record/realname-auth-record.service';
import { RealnameAuthRecord } from 'app/shared/model/realname-auth-record.model';

describe('Component Tests', () => {
    describe('RealnameAuthRecord Management Component', () => {
        let comp: RealnameAuthRecordComponent;
        let fixture: ComponentFixture<RealnameAuthRecordComponent>;
        let service: RealnameAuthRecordService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [RealnameAuthRecordComponent],
                providers: []
            })
                .overrideTemplate(RealnameAuthRecordComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(RealnameAuthRecordComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(RealnameAuthRecordService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new RealnameAuthRecord(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.realnameAuthRecords[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
