/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { DtlAppMockTestModule } from '../../../test.module';
import { IspComponent } from 'app/entities/isp/isp.component';
import { IspService } from 'app/entities/isp/isp.service';
import { Isp } from 'app/shared/model/isp.model';

describe('Component Tests', () => {
    describe('Isp Management Component', () => {
        let comp: IspComponent;
        let fixture: ComponentFixture<IspComponent>;
        let service: IspService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [IspComponent],
                providers: []
            })
                .overrideTemplate(IspComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(IspComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(IspService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Isp(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.isps[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
